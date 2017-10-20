# identitas-j
Make identifers

A set of libraries for coining, manipulating and checking identifiers.

An identifier is short string or integer which is used to represent other things, called here the identifed, such as database records, terms in an ontology or physical entities such as a people or proteins. Identifiers are semantics-free -- that is they do not depend on the characteristics of the thing that they represent; therefore, they can remain constant, even while the identified changes.

Identifier schemes can have many different characteristics, many of which conflict. A good identifier scheme, therefore, is one that makes the correct compromises for the requirements.

Identitas provides the following functionality:

* Proquint: functions to convert between numbers and an equivalent "pronouceable" version, following the http://arxiv.org/html/0901.4016 scheme.
* Damm: check-digits using the http://arxiv.org/html/0901.4016 algorithm.
* Core: combining all of these!
    
# Usage

# Proquint
    Proquint are a transformation from numbers to a more pronounceable form.
       Proquint.IntegerConvert(0);
       ;; Proint for: 0  is: babab-babab
       
       Proquint.IntegerConvert(Integer.MAX_VALUE); 
       ;; Proint for: 2147483647  is: luzuz-zuzuz
