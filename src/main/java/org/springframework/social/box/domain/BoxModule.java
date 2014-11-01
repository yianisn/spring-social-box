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
package org.springframework.social.box.domain;

import org.springframework.social.box.domain.internal.BoxEnterpriseMini;
import org.springframework.social.box.domain.internal.BoxIdentifiableObject;
import org.springframework.social.box.domain.internal.json.BoxEnterpriseMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxIdentifiableObjectMixin;
import org.springframework.social.box.domain.json.BoxUserMixin;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public BoxModule() {
        super("BoxModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(BoxIdentifiableObject.class, BoxIdentifiableObjectMixin.class);
        context.setMixInAnnotations(BoxEnterpriseMini.class, BoxEnterpriseMiniMixin.class);

        context.setMixInAnnotations(BoxUser.class, BoxUserMixin.class);
    }

}
