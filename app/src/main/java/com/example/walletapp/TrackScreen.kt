// TrackScreen.kt
package com.example.walletapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Модель статуса доставки
data class TrackingStatus(
    val id: Int,
    val title: String,
    val date: String,
    val isCompleted: Boolean,
    val isActive: Boolean = false
)

@Composable
fun TrackScreen(
    onBackClick: () -> Unit = {},
    onViewPackageInfo: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onWalletClick: () -> Unit = {},
    onTrackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // Данные статусов доставки
    val trackingStatuses = listOf(
        TrackingStatus(
            id = 1,
            title = "Courier requested",
            date = "July 7 2022 08:00am",
            isCompleted = true
        ),
        TrackingStatus(
            id = 2,
            title = "Package ready for delivery",
            date = "July 7 2022 08:30am",
            isCompleted = true
        ),
        TrackingStatus(
            id = 3,
            title = "Package in transit",
            date = "July 7 2022 10:30am",
            isCompleted = true,
            isActive = true
        ),
        TrackingStatus(
            id = 4,
            title = "Package delivered",
            date = "July 7 2022 03:30am",
            isCompleted = false
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // ===== Верхняя панель =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Назад"
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Заголовок
            Text(
                text = "Tracking Number",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Пустое место для баланса
            Box(modifier = Modifier.size(40.dp))
        }

        // ===== Номер отслеживания =====
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "R-7458-4567-4434-5854",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.5.sp
                )
            }
        }

        // ===== Заголовок "Package Status" =====
        Text(
            text = "Package Status",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // ===== Список статусов =====
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(trackingStatuses) { status ->
                TrackingStatusItem(
                    status = status,
                    isLast = status == trackingStatuses.last()
                )
            }
        }

        // ===== Кнопка "View Package Info" =====
        Button(
            onClick = onViewPackageInfo,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "View Package Info",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

// ===== Компонент: Элемент статуса =====
@Composable
fun TrackingStatusItem(
    status: TrackingStatus,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Левая часть: линия и кружок
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кружок
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        color = when {
                            status.isCompleted -> Color(0xFF4CAF50)
                            status.isActive -> Color(0xFFFF9800)
                            else -> Color(0xFFE0E0E0)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Галочка для завершенных
                if (status.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                } else if (status.isActive) {
                    // Точка для активного
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }
            }

            // Линия (если не последний элемент)
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(
                            color = if (status.isCompleted) Color(0xFF4CAF50) else Color(0xFFE0E0E0)
                        )
                )
            }
        }

        // Правая часть: текст
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp)
        ) {
            // Название статуса
            Text(
                text = status.title,
                fontSize = 16.sp,
                fontWeight = if (status.isCompleted || status.isActive)
                    FontWeight.Bold
                else
                    FontWeight.Normal,
                color = if (status.isCompleted || status.isActive)
                    Color.Black
                else
                    Color.Gray
            )

            // Дата
            Text(
                text = status.date,
                fontSize = 14.sp,
                color = if (status.isCompleted || status.isActive)
                    Color.Gray
                else
                    Color(0xFFBDBDBD)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrackScreen() {
    TrackScreen()
}