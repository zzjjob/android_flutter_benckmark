// Copyright 2012 Google Inc.
// All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// Translated from Dart's ton80 benchmark suite to Java
package defrac.benchmark.havlak;


import android.support.annotation.NonNull;
import java.util.ArrayList;

class BasicBlock {
  final int name;

  @NonNull
  final ArrayList<BasicBlock> inEdges  = new ArrayList();

  @NonNull
  final ArrayList<BasicBlock> outEdges = new ArrayList();

  BasicBlock(final int name) {
    this.name = name;
  }

  public int getNumPred() {
    return inEdges.size();
  }

  public void addInEdge(@NonNull final BasicBlock bb) {
    inEdges.add(bb);
  }
  public void addOutEdge(@NonNull final BasicBlock bb) {
    outEdges.add(bb);
  }
}
