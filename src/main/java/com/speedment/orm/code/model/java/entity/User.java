/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.orm.code.model.java.entity;

/**
 * An interface representing an entity (for example, a row) in the Table
 * 'myProject.myDbms.myCoolApp.user'.
 * <p>
 * This Class or Interface has been automatically generated by Speedment. Any
 * changes made to this Class or Interface will be overwritten.
 *
 * @author Speedment
 */
public interface User {

    String getFirstName();

    public interface Bean extends User {

        Bean setFirstName(String firstName);
    }

    public interface Builder extends User, com.speedment.orm.core.Builder<User> {

        Builder firstName(String firstName);
    }

}
