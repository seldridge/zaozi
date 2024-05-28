# Rationale

This document describes why things in this repository are done the way they are as well as a discussion of alternatives.
This is intended to be a historical document so that prior discussions are not forgotten.

## Context

Chisel3 used an internal `Builder` object that stores all runtime information about the Chisel circuit being elaborated.
This creates a number of problems:

1. This prevents any separation of compilation

## Thoughts from Jiuyang
Chisel3 serves as an embedded domain-specific language (eDSL) frontend for the CIRCT FIRRTL Dialect, albeit with distinct syntax and overhead. I aim to adopt the eDSL concept from Chisel for Zaozi while ensuring Zaozi remains as extensible and minimal as possible. This way, it can function as a versatile frontend for CIRCT-based eDSLs.

 - The core of Zaozi should be minimalist, excluding hardware implementations such as Queue and Arbiter, which should be provided by a separate standard library.
 - Zaozi's extensibility should be maximized, allowing users to integrate their preferred Dialects in CIRCT. Utilizing a C-API for lowering to CIRCT, it should serve as a universal frontend for diverse hardware architectures.

### Type System

Achieving deterministic type lowering is crucial for integrating different Dialects. We must devise methods to lower complex Chisel types into SystemVerilog structs, akin to a Type ABI.
 - Each type should have both static and runtime-dependent components. Static parts should map directly to CIRCT types (e.g., Record to structure, Vec to one-dimensional packed array, Enum to enum), while runtime-dependent types (e.g., io, reg, wire) should allow type elaboration based on both static and runtime information. Users should be empowered to define custom types and lowering methods.
 - Operators should be implemented as type classes in Scala, with users defining their lowering to CIRCT, facilitating the addition of custom operators.
 - Consideration should be given to introducing an Opaque Type for adding syntactic enhancements such as ECC, OneHot encoding, and different codings.
 - Integration of UVM verification with Zaozi's elaboration results may necessitate parameterization and elaboration support, albeit currently unsupported in CIRCT.

### Builder

A header file is essential for compilation separation, a feature lacking in Chisel. While Chisel offers features like D/I, Interface, and SerializableModule, they are not universally beneficial for users. In Zaozi, these features should be carefully designed for enhanced linking experiences.
 - Each Zaozi build should only elaborate a single module, with instantiation depending solely on the headers of other modules, while linking is deferred to CIRCT. Each Module should map directly to a CIRCT Module.
 - Every Module build should occur within a separate JVM runtime to simplify project complexity and delegate intricacies to the build system and CIRCT.
