(defproject com.brunobonacci/terrain "0.1.0"
  :description "Terrain simulation"

  :url "https://github.com/BrunoBonacci/terrain"

  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :scm {:name "git" :url "https://github.com/BrunoBonacci/terrain.git"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [quil "2.7.1"]]

  :main terrain.core

  :global-vars {*warn-on-reflection* true}

  )
