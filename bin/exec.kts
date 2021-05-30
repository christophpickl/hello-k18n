#!/usr/bin/env kscript
// see: https://github.com/holgerbrandl/kscript

// @file:MavenRepository("", "")
@file:CompilerOpts("-jvm-target 11")
@file:DependsOn("com.github.cpickl.hellok8s:build-tools:1.0-SNAPSHOT")

import hellokube.buildtools.*

sayHello()
//build(args)
