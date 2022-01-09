package gg.koo.kooggbot.utils

import discord4j.common.util.Snowflake
import discord4j.core.`object`.Invite

fun Invite.isValid(): Boolean {
    return guildId.get() == Snowflake.of(925033252580896768)
}