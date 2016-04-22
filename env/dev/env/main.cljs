(ns ^:figwheel-no-load env.main
  (:require [reagent.core :as r]
            [exp-cljstest.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(def cnt (r/atom 0))
(defn reloader [] @cnt [core/app-root])
(def root-el (r/as-element [reloader]))

(def dev-env (js/require "dev-env"))

(figwheel/watch-and-reload
 :websocket-url (str "ws://" (.-ip dev-env) ":3449/figwheel-ws")
 :heads-up-display false
 :jsload-callback #(swap! cnt inc))

(core/init)
