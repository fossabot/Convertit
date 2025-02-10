package com.nasahacker.convertit.util

/**
 * @author      Tamim Hossain
 * @email       tamimh.dev@gmail.com
 * @license     Apache-2.0
 *
 * ConvertIt is a free and easy-to-use audio converter app.
 * It supports popular audio formats like MP3 and M4A.
 * With options for high-quality bitrates ranging from 128k to 320k,
 * ConvertIt offers a seamless conversion experience tailored to your needs.
 */

object Constant {
    const val GITHUB_ISSUES_URL = "https://github.com/CodeWithTamim/ConvertIt/issues"
    const val STORAGE_PERMISSION_CODE = 101

    //const val PICK_FILE_REQUEST_CODE = 102
    val BITRATE_ARRAY = listOf("128k", "192k", "256k", "320k")
    val FORMAT_ARRAY = listOf(".mp3", ".m4a", ".wav", ".aac", ".ogg")
    const val URI_LIST = "uri_list"
    const val BITRATE = "bitrate"
    const val AUDIO_FORMAT = "audio_format"
    const val CONVERT_BROADCAST_ACTION = "com.nasahacker.convertit.ACTION_CONVERSION_COMPLETE"
    const val ACTION_STOP_SERVICE = "com.nasahacker.convertit.ACTION_STOP_SERVICE"
    const val IS_SUCCESS = "isSuccess"
    const val CHANNEL_ID = "CONVERT_IT_CHANNEL_ID"
    const val CHANNEL_NAME = "ConvertIt Notifications"
    const val FOLDER_DIR = "ConvertIt"
    const val DISCORD_CHANNEL = "https://discord.gg/wzkDG2jDDS"
    const val GITHUB_PROFILE = "https://github.com/codewithtamim"
    const val GITHUB_PROFILE_MOD = "https://github.com/moontahid"
    const val TELEGRAM_CHANNEL = "https://t.me/officialnasahacker"

    //   const val PROFILE_URL = "https://avatars.githubusercontent.com/u/132823494"
    const val APP_PREF = "app_prefs"
    const val PREF_DONT_SHOW_AGAIN = "pref_dont_show_again"
}