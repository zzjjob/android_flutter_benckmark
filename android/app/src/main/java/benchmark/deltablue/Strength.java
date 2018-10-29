// Copyright 2011 Google Inc. All Rights Reserved.
// Copyright 1996 John Maloney and Mario Wolczko
//
// This file is part of GNU Smalltalk.
//
// GNU Smalltalk is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by the Free
// Software Foundation; either version 2, or (at your option) any later version.
//
// GNU Smalltalk is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
// details.
//
// You should have received a copy of the GNU General Public License along with
// GNU Smalltalk; see the file COPYING.  If not, write to the Free Software
// Foundation, 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
//
// Translated first from Smalltalk to JavaScript, and finally to
// Dart by Google 2008-2010.
//
// Translated from Dart's ton80 benchmark suite to Java
package defrac.benchmark.deltablue;

import android.support.annotation.NonNull;

public final class Strength {
  @NonNull public static final Strength REQUIRED         = new Strength(0);
  @NonNull private static final Strength STRONG_REFERRED = new Strength(1);
  @NonNull public static final Strength PREFERRED        = new Strength(2);
  @NonNull public static final Strength STRONG_DEFAULT   = new Strength(3);
  @NonNull public static final Strength NORMAL           = new Strength(4);
  @NonNull private static final Strength WEAK_DEFAULT    = new Strength(5);
  @NonNull public static final Strength WEAKEST          = new Strength(6);

  @NonNull
  private static final Strength[] NEXT_WEAKER = {
      STRONG_REFERRED,
      PREFERRED,
      STRONG_DEFAULT,
      NORMAL,
      WEAK_DEFAULT,
      WEAKEST,
  };

  private final int value;

  private Strength(final int value) {
    this.value = value;
  }

  @NonNull
  public Strength nextWeaker() {
    return NEXT_WEAKER[value];
  }

  public static boolean stronger(@NonNull final Strength s1, @NonNull final Strength s2) {
    return s1.value < s2.value;
  }

  public static boolean weaker(@NonNull final Strength s1, @NonNull final Strength s2) {
    return s1.value > s2.value;
  }

  @NonNull
  public static Strength weakest(@NonNull final Strength s1, @NonNull final Strength s2) {
    return weaker(s1, s2) ? s1 : s2;
  }
}
