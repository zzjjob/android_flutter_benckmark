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

final class Vector {
  final double x, y, z;

  Vector(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @NonNull
  Vector normalize() {
    double m = magnitude();
    return new Vector(x / m, y / m, z / m);
  }

  @NonNull
  Vector negateY() {
    return new Vector(x, -y, z);
  }

  double magnitude() {
    return Math.sqrt((x * x) + (y * y) + (z * z));
  }

  @NonNull
  Vector cross(Vector w) {
    return new Vector(-z * w.y + y * w.z,
        z * w.x - x * w.z,
        -y * w.x + x * w.y);
  }

  double dot(Vector w) {
    return x * w.x + y * w.y + z * w.z;
  }

  @NonNull
  Vector add(Vector w) {
    return new Vector(w.x + x, w.y + y, w.z + z);
  }

  @NonNull
  Vector sub(Vector w) {
    return new Vector(x - w.x, y - w.y, z - w.z);
  }

  @NonNull
  Vector mul(Vector w) {
    return new Vector(x * w.x, y * w.y, z * w.z);
  }

  @NonNull
  Vector multiplyScalar(double w) {
    return new Vector(x * w, y * w, z * w);
  }

  @NonNull
  @Override
  public String toString() {
    return "Vector ["+x+", "+y+", "+z+"]";
  }
}
