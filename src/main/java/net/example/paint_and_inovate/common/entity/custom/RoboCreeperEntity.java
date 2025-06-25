package net.example.paint_and_inovate.common.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class RoboCreeperEntity extends Creeper {

    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState walkAnimation = new AnimationState();
    public final AnimationState runAnimation = new AnimationState();
    public final AnimationState boomAnimation = new AnimationState();

    private boolean isRunning = false;
    private boolean exploded = false;

    public RoboCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SwellGoal(this));
        this.goalSelector.addGoal(3, new ChasePlayerGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Creeper.createAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    private void setupAnimationStates() {
        if (this.isIgnited()) {
            if (!boomAnimation.isStarted()) {
                boomAnimation.start(this.tickCount);
            }
            runAnimation.stop();
            walkAnimation.stop();
            idleAnimation.stop();
        } else if (isRunning) {
            if (!runAnimation.isStarted()) {
                runAnimation.start(this.tickCount);
            }
            walkAnimation.stop();
            idleAnimation.stop();
            boomAnimation.stop();
        } else if (this.isMoving()) {
            if (!walkAnimation.isStarted()) {
                walkAnimation.start(this.tickCount);
            }
            runAnimation.stop();
            idleAnimation.stop();
            boomAnimation.stop();
        } else {
            if (!idleAnimation.isStarted()) {
                idleAnimation.start(this.tickCount);
            }
            walkAnimation.stop();
            runAnimation.stop();
            boomAnimation.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();

            if (this.isIgnited()) {
                int swell = this.getSwellDir();

                // Электрические искры и дым во время зарядки
                if (swell > 0 && swell % 3 == 0) {
                    Vec3 pos = this.position().add(
                            (this.random.nextDouble() - 0.5) * 0.5,
                            this.random.nextDouble() * 0.5 + 1.0,
                            (this.random.nextDouble() - 0.5) * 0.5
                    );
                    this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, pos.x, pos.y, pos.z, 0, 0.05, 0);
                    this.level().addParticle(ParticleTypes.SMOKE, pos.x, pos.y, pos.z, 0, 0.01, 0);
                }

                // В момент взрыва - мощная вспышка
                if (swell >= 30 && !exploded) {
                    spawnExplosionEffects();
                    exploded = true;
                }
            } else {
                exploded = false;
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        if (this.level().isClientSide() && this.isIgnited() && !exploded) {
            spawnExplosionEffects();
        }
        super.die(source);
    }

    private void spawnExplosionEffects() {
        // Основная мощная вспышка
        this.level().addParticle(
                ParticleTypes.FLASH,
                this.getX(),
                this.getY() + 1.5,
                this.getZ(),
                0, 0, 0
        );

        // Дополнительные вспышки в радиусе
        for (int i = 0; i < 20; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 3.5;
            double offsetY = this.random.nextDouble() * 2.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 3.5;

            this.level().addParticle(
                    ParticleTypes.FLASH,
                    this.getX() + offsetX,
                    this.getY() + offsetY,
                    this.getZ() + offsetZ,
                    0, 0, 0
            );
        }

        // Эффект разлетающихся искр
        for (int i = 0; i < 30; i++) {
            this.level().addParticle(
                    ParticleTypes.ELECTRIC_SPARK,
                    this.getX(),
                    this.getY() + 1.0,
                    this.getZ(),
                    (this.random.nextDouble() - 0.5) * 0.5,
                    this.random.nextDouble() * 0.5,
                    (this.random.nextDouble() - 0.5) * 0.5
            );
        }
    }

    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 0.001;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CREEPER_PRIMED;
    }

    static class ChasePlayerGoal extends Goal {
        private final RoboCreeperEntity creeper;
        private Player target;

        public ChasePlayerGoal(RoboCreeperEntity creeper) {
            this.creeper = creeper;
        }

        @Override
        public boolean canUse() {
            this.target = this.creeper.level().getNearestPlayer(this.creeper, 16.0);
            return this.target != null && !this.target.isCreative();
        }

        @Override
        public void start() {
            this.creeper.isRunning = true;
            this.creeper.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3D);
            this.creeper.getNavigation().moveTo(this.target, 1.5D);
        }

        @Override
        public void stop() {
            this.creeper.isRunning = false;
            this.creeper.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
            this.target = null;
        }

        @Override
        public void tick() {
            if (this.target != null && this.creeper.distanceToSqr(this.target) < 9.0D) {
                if (!this.creeper.isIgnited()) {
                    this.creeper.ignite();
                    this.creeper.exploded = false; // Сброс флага при новом взрыве
                }
                if (this.creeper.level().isClientSide()) {
                    for (int i = 0; i < 15; i++) {
                        double offsetX = (this.creeper.random.nextDouble() - 0.5) * 0.5;
                        double offsetY = this.creeper.random.nextDouble() * 0.5 + 1.0;
                        double offsetZ = (this.creeper.random.nextDouble() - 0.5) * 0.5;
                        this.creeper.level().addParticle(
                                ParticleTypes.ELECTRIC_SPARK,
                                this.creeper.getX() + offsetX,
                                this.creeper.getY() + offsetY,
                                this.creeper.getZ() + offsetZ,
                                0, 0.05, 0
                        );
                    }
                }
            } else {
                this.creeper.getNavigation().moveTo(this.target, 1.5D);
            }
        }
    }
}