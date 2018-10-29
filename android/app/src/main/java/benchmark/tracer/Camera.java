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

final class Camera {
  @NonNull
  final Vector position;
  @NonNull
  private final Vector lookAt;
  @NonNull
  private final Vector up;
  private final Vector equator;
  private final Vector screen;

  Camera(@NonNull final Vector position, @NonNull final Vector lookAt, @NonNull final Vector up) {
    this.position = position;
    this.lookAt = lookAt;
    this.up = up;
    equator = lookAt.normalize().cross(up);
    screen = position.add(lookAt);
  }

  @NonNull
  Ray getRay(double vx, double vy) {
    final Vector pos = screen.sub(equator.multiplyScalar(vx).sub(up.multiplyScalar(vy))).negateY();
    final Vector dir = pos.sub(position);
    return new Ray(pos, dir.normalize());
  }

  @NonNull
  @Override
  public String toString() {
    return "Camera []";
  }
}
