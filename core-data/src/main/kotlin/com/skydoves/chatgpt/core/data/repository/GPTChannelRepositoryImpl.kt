/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.chatgpt.core.data.repository

import com.skydoves.chatgpt.core.data.chat.chatGPTUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.utils.Result
import java.util.Random
import java.util.UUID
import javax.inject.Inject

internal class GPTChannelRepositoryImpl @Inject constructor(
  private val chatClient: ChatClient
) : GPTChannelRepository {

  override suspend fun createRandomChannel(): Result<Channel> {
    val userId = chatClient.getCurrentUser()?.id ?: ""
    return chatClient.createChannel(
      channelType = "messaging",
      channelId = UUID.randomUUID().toString(),
      memberIds = listOf(userId, chatGPTUser.id),
      extraData = mapOf("name" to "ChatGPT ${Random().nextInt(99999)}")
    ).await()
  }
}
