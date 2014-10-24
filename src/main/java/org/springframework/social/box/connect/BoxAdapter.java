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
package org.springframework.social.box.connect;

import org.springframework.social.box.api.Box;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxAdapter implements ApiAdapter<Box>{

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#test(java.lang.Object)
     */
    public boolean test(Box api) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#setConnectionValues(java.lang.Object, org.springframework.social.connect.ConnectionValues)
     */
    public void setConnectionValues(Box api, ConnectionValues values) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#fetchUserProfile(java.lang.Object)
     */
    public UserProfile fetchUserProfile(Box api) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#updateStatus(java.lang.Object, java.lang.String)
     */
    public void updateStatus(Box api, String message) {
        // TODO Auto-generated method stub

    }

}
