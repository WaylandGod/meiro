(ns meiro.wrap-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer :all]
            [meiro.wrap :refer :all]
            [meiro.core :as m]
            [meiro.backtracker :as backtracker]))


(deftest neighbors-test
  (let [grid (m/init 4 5)]
    (testing "Get neighbors at edges of the grid."
      (is (= #{[1 4] [1 1] [0 0] [2 0]} (set (neighbors grid [1 0]))))
      (is (= #{[0 0] [0 2] [1 1] [3 1]} (set (neighbors grid [0 1]))))
      (is (= #{[0 1] [0 4] [1 0] [3 0]} (set (neighbors grid [0 0]))))
      (is (= #{[0 0] [3 4] [1 4] [0 3]} (set (neighbors grid [0 4]))))
      (is (= #{[0 0] [3 4] [2 0] [3 1]} (set (neighbors grid [3 0]))))
      (is (= #{[3 3] [3 0] [2 4] [0 4]} (set (neighbors grid [3 4])))))
    (testing "Get neighbors to a cell inside a grid"
      (is (= #{[0 1] [1 0] [1 2] [2 1]} (set (neighbors grid [1 1]))))
      (is (= #{[1 3] [2 2] [2 4] [3 3]} (set (neighbors grid [2 3])))))))


(deftest neighbors-horizontal-test
  (let [grid (m/init 4 5)]
    (testing "Get neighbors at edges of the grid."
      (is (= #{[1 4] [1 1] [0 0] [2 0]}
             (set (neighbors-horizontal grid [1 0]))))
      (is (= #{[0 0] [0 2] [1 1]}
             (set (neighbors-horizontal grid [0 1]))))
      (is (= #{[0 1] [0 4] [1 0]}
             (set (neighbors-horizontal grid [0 0]))))
      (is (= #{[0 0] [1 4] [0 3]}
             (set (neighbors-horizontal grid [0 4]))))
      (is (= #{[3 4] [2 0] [3 1]}
             (set (neighbors-horizontal grid [3 0]))))
      (is (= #{[3 3] [3 0] [2 4]}
             (set (neighbors-horizontal grid [3 4])))))
    (testing "Get neighbors to a cell inside a grid"
      (is (= #{[0 1] [1 0] [1 2] [2 1]}
             (set (neighbors-horizontal grid [1 1]))))
      (is (= #{[1 3] [2 2] [2 4] [3 3]}
             (set (neighbors-horizontal grid [2 3])))))))


(deftest direction-test
    (testing "Cells at edges can link to opposite edge."
      (is (= :north (direction [0 3] [4 3])))
      (is (= :south (direction [3 1] [0 1])))
      (is (= :east (direction [5 5] [5 0])))
      (is (= :west (direction [4 0] [4 2]))))
    (testing "Cardinal directions when adjacent."
      (is (= :north (direction [2 3] [1 3])))
      (is (= :south (direction [3 1] [4 1])))
      (is (= :east (direction [5 1] [5 2])))
      (is (= :west (direction [4 3] [4 2])))))
