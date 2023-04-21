.data
.comm	a,4,4

.text
	.align 4
.globl  himom
himom:
himom_bb2:
himom_bb1:
	ret
.globl  abcd
abcd:
abcd_bb2:
abcd_bb3:
	movl	$0, %EAX
abcd_bb1:
	ret
.globl  abc
abc:
abc_bb2:
abc_bb1:
	ret
.globl  def
def:
def_bb2:
def_bb1:
	ret
.globl  ghi
ghi:
ghi_bb2:
ghi_bb1:
	ret
