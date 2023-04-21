(DATA  a)
(DATA  e)
(FUNCTION  himom  [(void )]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  abcd  [(void )]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 0)])
    (OPER 5 Mov [(r 2)]  [(r 3)])
    (OPER 6 Load [(r 4)]  [(s e)])
    (OPER 7 Store []  [(r 2)(s e)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  abc  [(int a)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  def  [(int a) (int b)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
(FUNCTION  ghi  [(int a) (int b) (int c)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
