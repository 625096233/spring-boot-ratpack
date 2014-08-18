/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springframework.boot.ratpack.cli.compiler;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.springframework.boot.cli.compiler.AstUtils;
import org.springframework.boot.cli.compiler.CompilerAutoConfiguration;
import org.springframework.boot.cli.compiler.DependencyCustomizer;

/**
 * {@link CompilerAutoConfiguration} for Ratpack.
 * 
 * @author Dave Syer
 */
public class RatpackCompilerAutoConfiguration extends CompilerAutoConfiguration {

	@Override
	public boolean matches(ClassNode classNode) {
		return AstUtils
				.hasAtLeastOneInterface(classNode,
						"ratpack.spring.groovy.internal.RatpackScriptActionFactory.GroovyRatpackSource")
				|| AstUtils.hasAtLeastOneAnnotation(classNode, "EnableRatpack");
	}

	@Override
	public void applyDependencies(DependencyCustomizer dependencies)
			throws CompilationFailedException {
		dependencies.add("io.ratpack:ratpack-groovy:0.9.7")
				.add("io.ratpack:ratpack-jackson:0.9.7").add("org.springframework.boot:spring-boot-ratpack:1.0.0.BUILD-SNAPSHOT");

	}

	@Override
	public void applyImports(ImportCustomizer imports) throws CompilationFailedException {
		imports.addStaticStars("ratpack.jackson.Jackson", "ratpack.spring.groovy.Groovy")
				.addImports("ratpack.spring.annotation.EnableRatpack");
	}

}