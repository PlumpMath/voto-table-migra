(ns voto-table-migra.core
  (:use [clojure.core.incubator :only [dissoc-in]])
  (:gen-class)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]
            [clojure.data.xml :as xml])
  (:import [java.util.Date]))

(def postgres-db {:subprotocol "postgresql"
                  :subname "//127.0.0.1:5432/voff"
                  :user "voff"
                  :password ""})

(defn take-timestamp-out [e]
  (let [xml (xml/parse-str (:xml e))
        {{t :t} :attrs} xml
        ts (if (= t nil) (.getTime (java.util.Date.)) (Long/parseLong t))
        ts* (java.sql.Timestamp. ts)
        ne (assoc e :ts ts* :xml xml)]
    (dissoc-in ne [:xml :attrs :t])))

(defn serialize-xml [v]
  (let [{{{dS :dS cand :c} :attrs} :xml} v
        ser (str "<X" (if-not (= cand nil) (str " c='" cand "'"))
                 (if-not (= dS nil) (str " dS='" dS)) "'/>")]
    (assoc v :xml ser)))

(defn insert-timestamped-votes [sq]
  (apply (partial j/insert! postgres-db :in_vote) sq))

(def in-vote-old (j/query postgres-db (s/select * :in_vote_old)))

(defn -main [& args]
  (map (comp println pr-str)
       (insert-timestamped-votes (map (comp serialize-xml take-timestamp-out) in-vote-old))))

#_(insert-timestamped-votes
 (for [i (range 0 200)]
   {:ts (java.sql.Timestamp. i) :xml "<X dS='1'/>" :voteremail "Dummy" :servicename "Sys/p/dummy"}))
