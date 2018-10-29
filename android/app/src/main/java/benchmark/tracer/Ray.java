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

final class Ray {
  final Vector position;
  final Vector direction;

  public Ray(final Vector p, final Vector d) {
    this.position = p;
    this.direction = d;
  }

  @Override
  @NonNull
  public String toString() {
    return "Ray ["+position+", "+direction+"]";
  }
}
