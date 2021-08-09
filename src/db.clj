(ns db)

(def ^:private todos (atom [{:id 1
                             :value "Taste JavaScript"
                             :completed? true}
                            {:id 2
                             :value "Buy a unicorn"
                             :completed? false}]))

(def ^:private id (atom (count @todos)))

(defn get-todos [] @todos)

(defn get [id]
  (->> @todos
       (filter #(= (:id %) id))
       first))
