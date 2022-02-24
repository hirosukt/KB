package love.chihuyu.kb.utils

import org.bukkit.entity.Player

object PlayerUtil {

    fun Player.isGround(): Boolean {
        return !location.subtract(.0, -.1, .0).block.isEmpty
    }
}