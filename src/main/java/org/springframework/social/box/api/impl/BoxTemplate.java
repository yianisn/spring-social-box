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
package org.springframework.social.box.api.impl;

import org.springframework.social.box.api.Box;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * This is the central class for interacting with box.
 *
 * @author Ioannis Nikolaou
 */
public class BoxTemplate extends AbstractOAuth2ApiBinding implements Box {

    public BoxTemplate(String accessToken) {
        super(accessToken);
    }

}
