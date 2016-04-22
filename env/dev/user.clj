(ns user
  (:use [figwheel-sidecar.repl-api :as ra]))
;; This namespace is loaded automatically by nREPL

;; read project.clj to get build configs
(def profiles (->> "project.clj"
                   slurp
                   read-string
                   (drop-while #(not= % :profiles))
                   (apply hash-map)
                   :profiles))

(def cljs-builds (get-in profiles [:dev :cljsbuild :builds]))

(defn write-dev-env-js
  "Update target/dev-env.js with dev server info (hostname etc.)"
  []
  (spit "target/dev-env.js"
        (str "/* @providesModule dev-env */\n"
             "module.exports = {\n"
             "  hostname: '" (.getHostName (java.net.InetAddress/getLocalHost)) "',\n"
             "  ip: '" (.getHostAddress (java.net.InetAddress/getLocalHost)) "',\n"
             "};\n")))

(defn start-figwheel
  "Start figwheel for one or more builds"
  [& build-ids]
  (ra/start-figwheel!
   {:build-ids  build-ids
    :all-builds cljs-builds})
  (write-dev-env-js)
  (ra/cljs-repl))

(defn stop-figwheel
  "Stops figwheel"
  []
  (ra/stop-figwheel!))
