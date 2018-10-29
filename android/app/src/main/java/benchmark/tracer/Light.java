// The ray tracer code in this file is written by Adam Burmister. It
// is available in its original form from:
//
//   http://labs.flog.co.nz/raytracer/
//
// Ported from the v8 benchmark suite by Google 2012.
//
// Translated from Dart's ton80 benchmark suite to Java
package defrac.benchmark.tracer;

import android.support.annotation.NonNull;

final class Light {
  @NonNull
  final Vector position;

  @NonNull
  final Color color;

  private final double intensity;

  Light(@NonNull final Vector position, @NonNull final Color color) {
    this(position, color, 10.0);
  }

  Light(@NonNull final Vector position, @NonNull final Color color, final double intensity) {
    this.position = position;
    this.color = color;
    this.intensity = intensity;
  }

}
