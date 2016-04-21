(defproject exp-cljstest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.5.1" :exclusions [cljsjs/react]]
                 [re-frame "0.6.0"]
                 [prismatic/schema "1.0.4"]]
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-6"]]

  :clean-targets ["target/"]
  :aliases {"prod-build" ^{:doc "Recompile code with prod profile."}
            ["do" "clean"
             ["with-profile" "prod" "cljsbuild" "once" "main"]]}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.0-6"]]
                   :source-paths ["src" "env/dev"]
                   :cljsbuild    {:builds {:main {:source-paths ["src" "env/dev"]
                                                  :figwheel     true
                                                  :compiler     {:output-to     "target/index.js"
                                                                 :main          "env.main"
                                                                 :output-dir    "target"
                                                                 :optimizations :none}}}}
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
             :prod {:cljsbuild {:builds {:main {:source-paths ["src" "env/prod"]
                                                :compiler     {:output-to     "target/index.js"
                                                               :main          "env.main"
                                                               :output-dir    "target"
                                                               :optimizations :simple}}}}}})
