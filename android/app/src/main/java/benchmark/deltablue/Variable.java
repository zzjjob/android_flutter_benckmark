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
import android.support.annotation.Nullable;
import java.util.ArrayList;

public final class Variable {
  @NonNull
  public final ArrayList<Constraint> constraints = new ArrayList();

  @Nullable
  public Constraint determinedBy;

  public int mark;

  @NonNull
  public Strength walkStrength = Strength.WEAKEST;

  public boolean stay = true;

  public int value;

  public Variable(final int value) {
    this.value = value;
  }

  public void addConstraint(@NonNull final Constraint c) {
    constraints.add(c);
  }

  public void removeConstraint(@NonNull final Constraint c) {
    constraints.remove(c);
    if(determinedBy == c) {
      determinedBy = null;
    }
  }
}
