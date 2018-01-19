package com.mc10inc.codetest.model

import org.threeten.bp.Instant
import java.util.*

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
data class Study(val id: UUID,
                 val displayName: String,
                 val title: String,
                 val createdTs: Instant)