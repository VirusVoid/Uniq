import org.junit.Test;

import org.junit.Assert.*

class BinaryTreeTest {
    @Test
    fun remove() {
        val binTree = BinaryTree<Int>()
        binTree.add(10)
        binTree.add(12)
        binTree.add(11)
        binTree.add(4)
        binTree.add(16)
        binTree.add(7)
        binTree.add(2)
        binTree.add(15)
        binTree.add(5)

        assertTrue(binTree.contains(7))
        assertTrue(binTree.remove(7))
        assertFalse(binTree.contains(7))
        assertTrue(binTree.checkInvariant())

        assertTrue(binTree.remove(11))
        assertFalse(binTree.contains(11))
        assertTrue(binTree.checkInvariant())

        assertTrue(binTree.remove(4))
        assertTrue(binTree.remove(16))
        assertFalse(binTree.contains(4))
        assertFalse(binTree.contains(16))
        assertTrue(binTree.checkInvariant())

        assertFalse(binTree.remove(13))
        assertFalse(binTree.contains(13))

        assertTrue(binTree.remove(5))
        assertFalse(binTree.contains(5))
    }

}
