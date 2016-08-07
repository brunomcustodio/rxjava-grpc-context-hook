/*
 * Copyright 2016 brunomcustodio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.bmcstdio.rxjava.hooks;

import io.grpc.Context;
import rx.functions.Action0;

/**
 * Decorates an {@link Action0} so that it executes within the current {@link Context}.
 */
public final class GrpcContextPropagatingAction implements Action0 {
  private final Runnable runnable;

  /**
   * Decorates an {@link Action0} so that it executes within the current {@link Context}.
   * @param action0 the {@link Action0} to decorate.
   */
  public GrpcContextPropagatingAction(final Action0 action0) {
    this.runnable = Context.current().wrap(new Runnable() {
      @Override
      public void run() {
        action0.call();
      }
    });
  }

  @Override
  public void call() {
    runnable.run();
  }
}