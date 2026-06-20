// DeliverySuccessScreen.kt
package com.example.walletapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeliverySuccessScreen(
    onBackClick: () -> Unit = {},
    onRateRiderClick: () -> Unit = {},
    onAddFeedbackClick: () -> Unit = {},
    onDoneClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onWalletClick: () -> Unit = {},
    onTrackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // Состояние для рейтинга
    var rating by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
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
                text = "Delivery Successful",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Пустое место для баланса
            Box(modifier = Modifier.size(40.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))

        // ===== Иконка успеха =====
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Success",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(72.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ===== Заголовок =====
        Text(
            text = "Your Item has been delivered successfully",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // ===== Оценка курьера (звезды) =====
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Заголовок "Rate Rider"
                Text(
                    text = "⭐ Rate Rider",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Звезды рейтинга
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(5) { index ->
                        val starNumber = index + 1
                        Icon(
                            imageVector = if (starNumber <= rating)
                                Icons.Default.Star
                            else
                                Icons.Default.StarBorder,
                            contentDescription = "Star $starNumber",
                            tint = if (starNumber <= rating)
                                Color(0xFFFFC107)
                            else
                                Color(0xFFE0E0E0),
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    rating = starNumber
                                    onRateRiderClick()
                                }
                        )
                    }
                }

                // Текст с выбранным рейтингом
                if (rating > 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (rating) {
                            1 -> "Ужасно 😡"
                            2 -> "Плохо 😞"
                            3 -> "Нормально 😐"
                            4 -> "Хорошо 😊"
                            5 -> "Отлично 🤩"
                            else -> ""
                        },
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ===== Поле для отзыва =====
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clickable { onAddFeedbackClick() },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Add feedback",
                        tint = Color(0xFF6200EE),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Add feedback",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // ===== Кнопка "Done" =====
        Button(
            onClick = onDoneClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Done",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeliverySuccessScreen() {
    DeliverySuccessScreen()
}