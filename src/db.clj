(ns db)

(def ^:private todos (atom (sorted-map 1 {:id 1
                                          :title "Taste htmx"
                                          :completed? true}
                                       2 {:id 2
                                          :title "Buy a unicorn"
                                          :completed? false})))

(def ^:private id (atom (count @todos)))

(defn todo-list [] (vals @todos))

(defn new-todo [title]
  (let [todo {:id (swap! id inc)
              :title title
              :completed? false}]
    (swap! todos assoc @id todo)
    todo))

(defn update-todo [id title]
  (swap! todos assoc-in [id :title] title)
  (@todos id))

(defn toggle-todo [id]
  (swap! todos update-in [id :completed?] not)
  (@todos id))

(defn delete-todo [id]
  (swap! todos dissoc id))

(defn remove-completed-todo []
  (reset! todos (->> @todos
                     (remove #(:completed? (val %)))
                     (into (sorted-map)))))
