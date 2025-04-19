#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulate;
uniform float GameTime;
uniform float Speed;

in vec2 texCoord;

out vec4 fragColor;

const float Frequency = 55;
const float _Speed = 1000;

float random( vec2 pos )
{
    return fract(sin(dot(pos, vec2(12.9898,78.233))) * 43758.5453);
}

float noise( vec2 pos )
{
    return random( floor( pos ) );
}

float value_noise( vec2 pos )
{
    vec2 p = floor( pos );
    vec2 f = fract( pos );

    float v00 = noise( p + vec2( 0.0, 0.0 ) );
    float v10 = noise( p + vec2( 1.0, 0.0 ) );
    float v01 = noise( p + vec2( 0.0, 1.0 ) );
    float v11 = noise( p + vec2( 1.0, 1.0 ) );

    vec2 u = f * f * ( 3.0 - 2.0 * f );

    return mix( mix( v00, v10, u.x ), mix( v01, v11, u.x ), u.y );
}

void main() {
    vec2 offsetY = texCoord + vec2( GameTime * Speed, 0.0 );
    vec2 offsetX = texCoord + vec2( -10.0, -GameTime * Speed );
    //float sineNoise = sin(_Frequency * texCoord.y - GameTime * _Speed);
    //float offset = sineNoise * Strength;
    vec2 coord = texCoord.xy;
    coord.x += value_noise(offsetX * Frequency) / 200.0;
    coord.y += value_noise(offsetY * Frequency) / 200.0;

    fragColor = texture(DiffuseSampler, coord) * ColorModulate;
}