/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
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
package net.oneandone.testlinkjunit.tljunit;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.net.URI;

import org.junit.Assume;
import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.slf4j.LoggerFactory;

public class EclipseIntegration {

    @Test
    @TestLink(externalId="ECLIPSE_TEST")
    public void test() {
        assertTrue(true);
    }

    @Test
    @TestLink(internalId = 5)
    @Ignore("Should be ignored")
    public void blockedBecauseOfIgnore() {
        assertTrue(true);
    }

    @Test
    @TestLink(internalId = 6)
    public void blockedBecauseOfFailingAssumption() {
        Assume.assumeTrue("This should be true", false);
    }

    @Test
    @TestLink(internalId = 7)
    public void failesWithMessage() {
        assertTrue("This fails with a message", false);
    }

    @Test
    @TestLink(internalId = 8)
    public void failesWithoutMessage() {
        assertTrue(false);
    }

    public static void main(String[] args) throws FileNotFoundException {
        final JUnitCore core = new JUnitCore();
        core.addListener(new TestLinkXmlRunListener());
        core.addListener(new TestLinkLoggingRunListener(LoggerFactory.getLogger("MYTESTLINK"), URI.create("http://testlink.sourceforge.net/demo/")));
        core.run(EclipseIntegration.class);
    }
}