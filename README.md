# voto-table-migra

A Votorola database migration script.

## Usage

Java >= 6 and [Leiningen 2!](http://leiningen.org/) needs to be installed.

Adjust database setting in src/voto-table-migra/core.clj.
Rename "public.in_vote" to "public.in_vote_old".
Run new version of Votorola to create new table schema.
Run `lein run`in this folder to execute. The table "public.in_vote" should now be filled with the data of the output.

## License

Copyright © 2013 Christian Weilbach

Distributed under the Eclipse Public License, the same as Clojure.
