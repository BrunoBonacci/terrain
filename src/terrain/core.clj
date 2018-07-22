(ns terrain.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;
;; A Clojure/Quil implementation of the Terrain simulation coding
;; challenge presented here:
;; https://www.youtube.com/watch?v=IKB1hWWedMk
;;

(def xsize 1200)
(def ysize 1200)
(def scale 10)
(def xview 600)
(def yview 600)
(def speed -0.05)


(def rows (/ ysize scale))
(def cols (/ xsize scale))


(defn noise-range
  [min max]
  (fn [x y step]
    (let [x (* x 0.008)
          y (+ (* y 0.008) step)]
      (+ min (* (- max min) (q/noise x y))))))


(def noise (noise-range -100 150))

(defn noise-field
  [step]
  (->>
   (for [x (range 0 xsize scale)
       y (range 0 ysize scale )]
     [[x y] (noise x y step)])
   (into {})))


(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  0.0)


(defn update-state
  [state]
  (+ state speed))


(defn draw [state]
  (let [terrain (noise-field state)]
    (q/background 0)
    (q/stroke-float 255)
    (q/no-fill)
    ;;(q/fill 87 35 47)
    (q/with-translation [(/ xview 2) (/ yview 2)]
      (q/with-rotation [(q/radians 60) 1 0 0]
        (q/with-translation [(- (/ xsize 2)) (- (/ xsize 2))]
          (doseq [y (range 0 ysize scale )]
            (q/begin-shape :triangle-strip)
            (doseq [x (range 0 xsize scale)]
              (q/vertex x y (get terrain [x y] 0))
              (q/vertex x (+ y scale) (get terrain [x (+ y scale)] 0)))
            (q/end-shape)))))))


(defn -main [& args]
  (q/defsketch terrain
    :title "terrain simulation"
    :renderer :p3d
    :size [xview yview]
    :setup setup
    :update update-state
    :draw draw
    :features [:keep-on-top]
    :middleware [m/fun-mode]))

;;(-main)
