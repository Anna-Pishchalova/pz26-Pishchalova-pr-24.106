// PaymentMethodScreen.kt
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
fun PaymentMethodScreen(
    onBackClick: () -> Unit = {},
    onProceedClick: (String) -> Unit = {}
) {
    // Выбранный метод оплаты
    var selectedMethod by remember { mutableStateOf<String?>(null) }

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
                text = "Add Payment method",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Пустое место для баланса
            Box(modifier = Modifier.size(40.dp))
        }

        // ===== Список способов оплаты =====
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Вариант 1: Pay with wallet
            PaymentOptionCard(
                icon = "💳",
                title = "Pay with wallet",
                description = "complete the payment using your e wallet",
                isSelected = selectedMethod == "wallet",
                onClick = { selectedMethod = "wallet" }
            )

            // Вариант 2: Credit/debit card
            PaymentOptionCard(
                icon = "🏦",
                title = "Credit/debit card",
                description = "complete the payment using your debit card",
                isSelected = selectedMethod == "card",
                onClick = { selectedMethod = "card" },
                cardNumber = "*******3323"
            )

            // Вариант 3: Дополнительная карта (из дизайна)
            PaymentOptionCard(
                icon = "🏦",
                title = "Credit/debit card",
                description = "complete the payment using your debit card",
                isSelected = selectedMethod == "card2",
                onClick = { selectedMethod = "card2" },
                cardNumber = "*******1547"
            )

            Spacer(modifier = Modifier.weight(1f))

            // ===== Кнопка "Proceed to pay" =====
            Button(
                onClick = {
                    selectedMethod?.let {
                        onProceedClick(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedMethod != null) Color(0xFF6200EE) else Color(0xFFE0E0E0),
                    contentColor = if (selectedMethod != null) Color.White else Color.Gray
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = selectedMethod != null
            ) {
                Text(
                    text = "Proceed to pay",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ===== Компонент: Карточка способа оплаты =====
@Composable
fun PaymentOptionCard(
    icon: String,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    cardNumber: String? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFEDE7F6) else Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Иконка
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (isSelected) Color(0xFF6200EE) else Color(0xFFF5F5F5)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 24.sp
                )
            }

            // Текст
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Радио-кнопка
                    RadioButton(
                        selected = isSelected,
                        onClick = onClick,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF6200EE)
                        )
                    )
                }

                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                // Номер карты (если есть)
                if (cardNumber != null) {
                    Text(
                        text = cardNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6200EE)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentMethodScreen() {
    PaymentMethodScreen()
}