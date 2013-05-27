(defproject voto-table-migra "0.0.1"
  :description "Table migration script for Votorola."
  :url "http://zelea.com/project/votorola/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.xml "0.0.7"]
                 [org.clojure/core.incubator "0.1.2"]
                 [org.clojure/java.jdbc "0.3.0-alpha4"]
                 [postgresql/postgresql "9.1-901.jdbc4"]  ; 8.4-702.jdbc4
                 ]
;  :repl-options {:port 4555}
  :main voto-table-migra.core
                 )
