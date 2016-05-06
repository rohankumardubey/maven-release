package org.apache.maven.shared.release.transform.jdom;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.junit.Assert.*;

import java.io.StringReader;

import org.apache.maven.model.Model;
import org.apache.maven.model.Scm;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.junit.Test;

public class JDomModelTest
{
    private SAXBuilder builder = new SAXBuilder();
    
    @Test
    public void testGetScm() throws Exception
    {
        String content = "<project></project>";
        Document document = builder.build( new StringReader( content ) );
        assertNull( new JDomModel( document ).getScm() );
    }
    
    @Test
    public void testSetScm() throws Exception
    {
        String content = "<project></project>";
        Document document = builder.build( new StringReader( content ) );
        Model model = new JDomModel( document );
        assertNull( model.getScm() );
        
        model.setScm( new Scm() );
        assertNull( model.getScm().getConnection() );
        assertNull( model.getScm().getDeveloperConnection() );
        assertEquals( "HEAD", model.getScm().getTag() ); // legacy default
        assertNull( model.getScm().getUrl() );
        
        model.getScm().setConnection( "CONNECTION" );
        assertEquals( "CONNECTION", model.getScm().getConnection() );
        model.getScm().setDeveloperConnection( "DEVELOPERCONNECTION" );
        assertEquals(  "DEVELOPERCONNECTION", model.getScm().getDeveloperConnection() );
        model.getScm().setTag( "TAG" );
        assertEquals( "TAG", model.getScm().getTag() );
        model.getScm().setUrl( "URL" );
        assertEquals( "URL", model.getScm().getUrl() );
        
        model.setScm( null );
        assertNull( model.getScm() );
    }
    

}
