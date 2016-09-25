;;; Name: Urmila Sharma
;;; Student ID: 2436084
;;; CSCI 4525
;;; A global variable that keeps track of how many calls are made to #'satisfiable.
(defvar *calls* 0)

;;; Defines how many calls are allowed to #'satisfiable before an error is thrown.
(defvar *max-calls* 10)

(defmacro defun-limited (name args &body body)
  "Defines a function which can only be called *max-calls* number of times."
  `(defun ,name ,args
     (let ((result nil))
       (progn (setq *calls* (1+ *calls*))
              (when (> *calls* *max-calls*)
                (error "Call limit exceeded."))
              (setq result ,@body)
              (setq *calls* (1- *calls*))
              result))))

(defun grade (problem)
  "A function for grading an individual problem instance."
  (let ((number (first problem))
        (expression (second problem))
        (correct-answer (third problem))
        (your-answer 'error))
    (unwind-protect
        (setq your-answer (satisfiable (second problem)))
      (format t "Expression ~S: ~S~%  Correct Answer: ~S~%  Your Answer:    ~S~%" number expression correct-answer your-answer))))

;;; ---------- BEGIN STUDENT CODE ----------
;;; Your code will be pasted in starting here.

;;; Students enrolled in CSCI 4525 should modify this function to return the
;;; correct value, rather than nil every time.  You should comment out the
;;; limited definition of this function below.
(defun satisfiable (cnf)
 (unit-propagation (pure-literal-elimination cnf (get-literal cnf)))
)

  (defun set-variable (cnf variable value)
    (if
        (symbolp cnf)
        (if (eq cnf variable) value cnf)
        (mapcar 
            (lambda (part)
                (set-variable part variable value)
            )
            
        cnf)
        
    )
    )

(defun simplify (cnf)
;;;test either it is symbol or listing
    (if (symbolp cnf) cnf 
    ;;;we will use map to each of the item in the list.

        (let ((simplified (mapcar #'simplify cnf)))
             (cond
                ((eq (first simplified) 'not) 
                    (cond
                        ((eq (second simplified) t) nil)
                        ((eq (second simplified) nil) t)
                        (t simplified)
                    )
                )
                ((eq (first simplified) 'and)
                    (let((shorter (remove t simplified)))
                        (cond
                            ((equal shorter '(and)) t)
                            ((member nil shorter) nil)
                            (t shorter)
                        )
                    )
                )
                ((eq (first simplified) 'or)
                    (let((shorter (remove nil simplified)))
                        (cond
                            ((equal shorter '(or)) nil)
                            ((member t shorter) t)
                            (t shorter)
                        )
                    )
                )
            )
        )
    )
    
)
(defun conjuction (conjunction) (rest conjunction))

(defun unit-literal (cnf)
   (if (symbolp cnf) cnf
        (dolist (element (conjuction cnf))
            (if (symbolp element)
                
                (return element)
                
                (if (eq (first element) 'not ) 
                
                    (return element) 
                    
                    (if (and(eq (first element)'or) (eq (list-length element)2) ) 
                    
                        (return (second element))
                    )
                
                )
           )
      )
   )                                        
 )

(defun unit-propagate (cnf)
        (if (symbolp (unit-literal cnf))

            (if (eq (unit-literal cnf) t) t

            (if (eq (unit-literal cnf) nil) nil

            (simplify (set-variable cnf (unit-literal cnf) t))
            )
         )
            
            (if (eq (first (unit-literal cnf))'not)

              (let ((shorter (second (unit-literal cnf))))

                   (simplify (set-variable cnf shorter nil))
                 )
                
              )
           )
 )

 (defun pure-literal (cnf variable)
    (if (symbolp variable)
        (if (symbolp cnf)
            (if (eq cnf variable) variable
             cnf
            )
            (if (and (eq(first cnf)'not ) (eq variable (second cnf)))
                nil
                (if (member 'nil (mapcar (lambda (part)
                    (pure-literal part variable))
                cnf)) nil
                variable
                )
            )
       )
        (if (symbolp cnf)
            (if (eq cnf (second variable)) nil
             cnf
            )
            (if (equal cnf variable)
                variable
                (if (member 'nil (mapcar (lambda (part)
                    (pure-literal part variable)
                ) cnf)) nil
                variable
                )
              )
           )
        )
  )

(defun unit-propagation (cnf)
     (let ((reduced (unit-propagate cnf)))
        (if (symbolp  reduced) reduced
          (if (equal cnf reduced)
             cnf
            (unit-propagation reduced)
           )
         )
       )
 )

(defun get-literal (cnf)
    (let ((shorter (cond ((symbolp cnf)
       (list cnf))
    ((eq (first cnf)'not) (list cnf))
        (t (append (get-literal (first cnf)) (get-literal (rest cnf))))
    )))
       (cond ((member 'and shorter) (remove 'and shorter))
             ((member 'or shorter) (remove 'or shorter))
             ((member 'nil shorter) (remove 'nil shorter))
             (t shorter)
        )
     )
)

(defun pure-literal-elimination (cnf pure-literals)
    (if (> (list-length pure-literals) 0)
        (if (equal (first pure-literals) (pure-literal cnf (first pure-literals)))
                (pure-literal-elimination (simplify (set-variable cnf (first pure-literals) t)) (rest pure-literals))
                (pure-literal-elimination cnf (rest pure-literals))
        )
        cnf
       )
)

;;;end of the code


        

;;; Students enrolled in CSCI 5525 should modify this function to return the
;;; correct value, rather than nil every time.  You should comment out the
;;; unlimited definition of this function above.
;;;(defun-limited satisfiable (cnf)
;;; nil)

;;; Feel free to define other functions here if you want.
  
;;; ---------- END STUDENT CODE ----------

;;; Test the #'satisfiable function on various different CNF expressions.
;;; Some examples are provided here, but the actual problems that you will be
;;; graded on will be different from these.  However, if you can solve all of
;;; these correctly and (for those in CSCI 5525, without exceeding the call
;;; limit), then you will probably pass all the graded tests.
;;; The format of a test is: (number expression expected-value).
;;; For example, expression #6 is (and a b) and it is satisfiable, so the
;;; expected return value of #'satisfiable is t.
(mapcar #'grade '((1 t t)
                  (2 nil nil)
                  (3 a t)
                  (4 (and t) t)
                  (5 (and nil) nil)
                  (6 (and a b) t)
                  (7 (or t) t)
                  (8 (or nil) nil)
                  (9 (or a b) t)
                  (10 (and a (not a)) nil)
                  (11 (and a (or (not a) b)) t)
                  (12 (and a b (or (not a) (not b))) nil)
                  (13 (and (or a b c) (not b) (or (not a) c) (or a d) (not c)) nil)
                  (14 (and (not a) (not b) (not c) (not d) (not e) (not f) (not g) (not h) (not i) (not j) (or a b c d e f g h i j k)) t)))
