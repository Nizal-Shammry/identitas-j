# identitas-j
Make identifers

A set of libraries for coining, manipulating and checking identifiers. More details available at https://arxiv.org/abs/1709.09021

An identifier is short string or integer which is used to represent other things, called here the identifed, such as database records, terms in an ontology or physical entities such as a people or proteins. Identifiers are semantics-free -- that is they do not depend on the characteristics of the thing that they represent; therefore, they can remain constant, even while the identified changes.

Identifier schemes can have many different characteristics, many of which conflict. A good identifier scheme, therefore, is one that makes the correct compromises for the requirements.

Identitas provides the following functionality:

* Proquint: converts between proquint, and decimal strings. Following the http://arxiv.org/html/0901.4016 scheme. 
* Damm: check-digits using the https://en.wikibooks.org/wiki/Algorithm_Implementation/Checksums/Damm_Algorithm algorithm.
* Util: functions to convert between numbers (Integer, Short, Long) and an equivalent "pronouceable" version. 
    
# Usage

# Proquint
  Proquint are a transformation from numbers to a more pronounceable form.
          
          Util.Int_to_proint(0);
           ;; babab-babab
          Util.Int_to_proint(Integer.MAX_VALUE); 
           ;;  luzuz-zuzuz
          Util.Int_to_proint(Integer.MIN_VALUE);
           ;; mabab-babab
       
       
As an extension to original algorithm, we also provide support short and long transformations, useful depending on the size of identifier space required.
    
           Util.Short_to_prshort(0);
            ;;  babab
           Util.Short_to_prshort(Short.MAX_VALUE);
            ;; luzuz
           Util.Short_to_prshort(Short.MIN_VALUE);
            ;; mabab
     
           Util.Long_to_prolong(0);
            ;; babab-babab-babab-babab
           Util.Long_to_prolong(Long.MAX_VALUE);
            ;; luzuz-zuzuz-zuzuz-zuzuz
           Util.Long_to_prolong(Long.MIN_VALUE);
           ;; mabab-babab-babab-babab
        
