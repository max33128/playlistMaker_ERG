package com.example.playlistmaker.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playlistmaker.R
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    // 1. Получаем все строковые ресурсы ЗАРАНЕЕ, здесь, в Composable-контексте
    val shareText = stringResource(R.string.share_text)
    val developerEmail = stringResource(R.string.developer_email)
    val emailSubject = stringResource(R.string.email_subject)
    val emailBody = stringResource(R.string.email_body)
    val termsUrl = stringResource(R.string.terms_url)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Поделиться приложением
            ListItem(
                headlineContent = { Text(stringResource(R.string.share_app)) },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.clickable {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        // Используем заранее полученную переменную shareText
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            )

            // Написать разработчикам
            ListItem(
                headlineContent = { Text(stringResource(R.string.write_to_developers)) },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.clickable {
                    val uri = Uri.parse("mailto:$developerEmail")
                        .buildUpon()
                        .appendQueryParameter("subject", emailSubject)
                        .appendQueryParameter("body", emailBody)
                        .build()

                    val intent = Intent(Intent.ACTION_SENDTO, uri)
                    // Проверка на наличие почтового клиента не обязательна в новых API, но желательна
                    // try-catch блок нужен на случай, если нет почтового приложения
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // Можно добавить Toast с сообщением об ошибке
                    }
                }
            )

            // Пользовательское соглашение
            ListItem(
                headlineContent = { Text(stringResource(R.string.user_agreement)) },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termsUrl.trim()))
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // Можно добавить обработку ошибок
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PlaylistMakerTheme {
        SettingsScreen(onBackClick = {})
    }
}