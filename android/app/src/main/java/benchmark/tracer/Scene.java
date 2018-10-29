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
import java.util.List;
import java.util.ArrayList;

final class Scene {
  @NonNull
  private static final Vector POSITION = new Vector(0.0, 0.0, -0.5);
  @NonNull
  private static final Vector LOOK_AT = new Vector(0.0, 0.0, 1.0);
  @NonNull
  private static final Vector UP = new Vector(0.0, 1.0, 0.0);
  @NonNull
  private static final Color COLOR = new Color(0.0, 0.0, 0.5);
  @NonNull
  private static final Background BACKGROUND = new Background(COLOR, 0.2);

  @NonNull
  Camera camera;
  @NonNull
  final List<BaseShape> shapes;
  @NonNull
  final List<Light> lights;
  @NonNull
  Background background;
  Scene() {
    camera = new Camera(POSITION, LOOK_AT, UP);
    shapes = new ArrayList();
    lights = new ArrayList();
    background = BACKGROUND;
  }
}
