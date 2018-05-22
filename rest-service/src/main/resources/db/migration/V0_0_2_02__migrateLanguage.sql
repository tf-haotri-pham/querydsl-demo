UPDATE ${schema}.film SET original_language = 'en' WHERE original_language = 'English';
UPDATE ${schema}.film SET original_language = 'fr' WHERE original_language = 'French';
UPDATE ${schema}.film SET original_language = null 
  WHERE original_language IS NOT NULL AND original_language != 'en' AND original_language != 'fr';