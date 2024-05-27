# Rationale

This document describes why things in this repository are done the way they are as well as a discussion of alternatives.
This is intended to be a historical document so that prior discussions are not forgotten.

## Context

Chisel3 used an internal `Builder` object that stores all runtime information about the Chisel circuit being elaborated.
This creates a number of problems:

1. This prevents any separation of compilation
