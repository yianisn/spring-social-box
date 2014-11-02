/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.box.api;

import java.util.List;

import org.springframework.social.box.api.impl.UserTemplate.BoxUserFields;
import org.springframework.social.box.domain.BoxUser;

/**
 *
 * @author Ioannis Nikolaou
 */
public interface UserOperations {

    /**
     * Retrieves all the information about the user who is currently logged in i.e.
     * the user for whom this auth token was generated.
     * @return the user's profile information.
     */
    public BoxUser getUserInformation();

    /**
     * Retrieves a subset of the information about the user who is currently logged in i.e.
     * the user for whom this auth token was generated. The fields that can be used to define
     * the subset are defined in {@link BoxUserFields}
     * @param fields A list of the fields of the user information data that will be returned.
     * @return
     */
    public BoxUser getUserInformation(List<BoxUserFields> fields);

}
