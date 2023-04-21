(FUNCTION  test  [(void )]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 Mov [(r 2)]  [(r 4)])
    (OPER 6 Mov [(r 5)]  [(i 1)])
    (OPER 7 Mov [(r 3)]  [(r 5)])
    (OPER 8 Mov [(r 6)]  [(i 0)])
    (OPER 9 Mov [(r 3)]  [(r 6)])
    (OPER 10 Mov [(r 2)]  [(r 6)])
    (OPER 11 GT []  [(r 2)(r 3)])
    (OPER 12 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@7cd84586)]  [])
  )
  (BB 4
    (OPER 14 Mov [(r 7)]  [(i 1)])
    (OPER 13 Sub_I []  [(r 3)(r 7)])
    (OPER 15 Mov [(r 3)]  [(r 0)])
  )
  (BB 5
    (OPER 45 EQ []  [(r 2)(r 3)])
    (OPER 46 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@30dae81)]  [])
  )
  (BB 14
    (OPER 47 EQ []  [(r 2)(r 3)])
    (OPER 48 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@1b2c6ec2)]  [])
  )
  (BB 16
    (OPER 49 EQ []  [(r 2)(r 3)])
    (OPER 50 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@4edde6e5)]  [])
  )
  (BB 18
    (OPER 52 Mov [(r 18)]  [(i 1)])
    (OPER 51 Sub_I []  [(r 3)(r 18)])
    (OPER 53 Mov [(r 2)]  [(r 0)])
    (OPER 54 EQ []  [(r 2)(r 3)])
    (OPER 55 BEQ [(bb com.bd.compiler.lowlevel.BasicBlock@70177ecd)]  [])
  )
  (BB 19
    (OPER 56 EQ []  [(r 2)(r 3)])
    (OPER 57 BEQ [(bb com.bd.compiler.lowlevel.BasicBlock@1e80bfe8)]  [])
  )
  (BB 17
  )
  (BB 15
    (OPER 58 Mov [(r 19)]  [(i 3)])
    (OPER 59 Mov [(r 3)]  [(r 19)])
    (OPER 60 Mov [(m RetReg)]  [(r null)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 11
    (OPER 29 Mov [(r 13)]  [(i 1)])
    (OPER 30 Mov [(r 3)]  [(r 13)])
    (OPER 32 Mov [(r 14)]  [(i 1)])
    (OPER 31 EQ []  [(r 3)(r 14)])
    (OPER 33 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@66a29884)]  [])
  )
  (BB 12
    (OPER 35 Mov [(r 15)]  [(i 2)])
    (OPER 34 Add_I []  [(r 3)(r 15)])
    (OPER 36 Mov [(r 3)]  [(r 0)])
    (OPER 38 Mov [(r 16)]  [(i 1)])
    (OPER 37 EQ []  [(r 3)(r 16)])
    (OPER 39 BEQ [(bb com.bd.compiler.lowlevel.BasicBlock@4769b07b)]  [])
  )
  (BB 13
  )
  (BB 6
    (OPER 16 Mov [(r 8)]  [(i 2)])
    (OPER 17 Mov [(r 3)]  [(r 8)])
    (OPER 19 Mov [(r 9)]  [(i 2)])
    (OPER 18 EQ []  [(r 3)(r 9)])
    (OPER 20 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@cc34f4d)]  [])
  )
  (BB 7
    (OPER 21 Mov [(r 10)]  [(i 2)])
    (OPER 22 Mov [(r 3)]  [(r 10)])
    (OPER 24 Mov [(r 11)]  [(i 2)])
    (OPER 23 EQ []  [(r 3)(r 11)])
    (OPER 25 BNE [(bb com.bd.compiler.lowlevel.BasicBlock@17a7cec2)]  [])
  )
  (BB 9
    (OPER 27 Mov [(r 12)]  [(i 1)])
    (OPER 26 Add_I []  [(r 3)(r 12)])
    (OPER 28 Mov [(r 3)]  [(r 0)])
  )
  (BB 10
    (OPER 42 Mov [(r 17)]  [(i 2)])
    (OPER 41 EQ []  [(r 3)(r 17)])
    (OPER 43 BEQ [(bb com.bd.compiler.lowlevel.BasicBlock@65b3120a)]  [])
  )
  (BB 8
  )
)
