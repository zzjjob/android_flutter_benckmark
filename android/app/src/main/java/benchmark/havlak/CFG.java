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
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CFG {
  @NonNull
  private final Map<Integer, BasicBlock> basicBlockMap = new HashMap();

  @NonNull
  private final ArrayList<BasicBlockEdge> edgeList = new ArrayList();

  @Nullable
  BasicBlock startNode;

  public BasicBlock createNode(final int name) {
    BasicBlock node = basicBlockMap.get(name);

    if (node == null) {
      node = new BasicBlock(name);
      basicBlockMap.put(name, node);
    }

    if (getNumNodes() == 1) {
      startNode = node;
    }

    return node;
  }

  public void addEdge(@NonNull final BasicBlockEdge edge) { edgeList.add(edge); }
  public int getNumNodes() { return basicBlockMap.size(); }
}
