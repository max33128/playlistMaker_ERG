package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistMakerTheme {
                SettingsScreen(
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
        ) {
            // 1. Поделиться приложением
            SettingsItem(
                title = stringResource(id = R.string.share_app),
                icon = Icons.Default.Share,
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            context.getString(R.string.share_text)
                        )
                        type = "text/plain"
                    }
                    context.startActivity(
                        Intent.createChooser(
                            shareIntent,
                            context.getString(R.string.share_app)
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2. Написать разработчикам
            SettingsItem(
                title = stringResource(id = R.string.write_to_developers),
                icon = Icons.Default.Email,
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(
                            Intent.EXTRA_EMAIL,
                            arrayOf(context.getString(R.string.developer_email))
                        )
                        putExtra(
                            Intent.EXTRA_SUBJECT,
                            context.getString(R.string.email_subject)
                        )
                        putExtra(
                            Intent.EXTRA_TEXT,
                            context.getString(R.string.email_body)
                        )
                    }

                    // Переносим try-catch в отдельную лямбду
                    val launchEmail = {
                        try {
                            context.startActivity(
                                Intent.createChooser(
                                    emailIntent,
                                    context.getString(R.string.write_to_developers)
                                )
                            )
                        } catch (e: Exception) {
                            // Обработка ошибки
                        }
                    }

                    launchEmail()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Пользовательское соглашение
            SettingsItem(
                title = stringResource(id = R.string.user_agreement),
                icon = Icons.Default.Description,
                onClick = {
                    val url = context.getString(R.string.terms_url)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                    // Переносим try-catch в отдельную лямбду
                    val launchBrowser = {
                        try {
                            context.startActivity(
                                Intent.createChooser(
                                    intent,
                                    context.getString(R.string.user_agreement)
                                )
                            )
                        } catch (e: Exception) {
                            // Обработка ошибки
                        }
                    }

                    launchBrowser()
                }
            )
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    icon: ImageVector? = null,
    hasIcon: Boolean = true,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() },
        color = Color.White,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (hasIcon && icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Перейти",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}