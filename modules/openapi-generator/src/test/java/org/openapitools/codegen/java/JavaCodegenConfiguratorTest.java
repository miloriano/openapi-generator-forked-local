/*
 * Copyright 2018 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 * Copyright 2018 SmartBear Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openapitools.codegen.java;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.*;
import org.openapitools.codegen.CodegenConstants;
import org.openapitools.codegen.CodegenModel;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.TestUtils;
import org.openapitools.codegen.languages.AbstractJavaCodegen;
import org.openapitools.codegen.utils.ModelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class JavaCodegenConfiguratorTest {

    @Test
    public void generateModel() throws Exception {
        String inputSpec = "src/test/resources/3_0/petstore.json";

        final File output = Files.createTempDirectory("test").toFile();
        output.deleteOnExit();

        Assert.assertTrue(new File(inputSpec).exists());

        final CodegenConfigurator configurator = new CodegenConfigurator()
                .setGeneratorName("java")
                .setLibrary("jersey2")
                //.addAdditionalProperty("withXml", true)
                .addAdditionalProperty(CodegenConstants.SERIALIZABLE_MODEL, true)
                .setInputSpec(inputSpec)
                .setOutputDir(output.getAbsolutePath());

        final ClientOptInput clientOptInput = configurator.toClientOptInput();
        new DefaultGenerator().opts(clientOptInput).generate();

        File orderFile = new File(output, "src/main/java/org/openapitools/client/model/Order.java");
        Assert.assertTrue(orderFile.exists());
    }
}
