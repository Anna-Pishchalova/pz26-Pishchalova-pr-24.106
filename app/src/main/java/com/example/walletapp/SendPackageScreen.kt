// SendPackageScreen.kt
package com.example.walletapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SendPackageScreen(
    onBackClick: () -> Unit = {},
    onReportClick: () -> Unit = {},
    onSuccessfulClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onWalletClick: () -> Unit = {},
    onTrackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
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
                text = "Send a package",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Пустое место для баланса
            Box(modifier = Modifier.size(40.dp))
        }

        // ===== Основной контент с прокруткой =====
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ===== Package Information =====
            item {
                Text(
                    text = "Package Information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // ===== Origin details =====
            item {
                InfoCard(
                    title = "Origin details",
                    icon = "📍",
                    details = listOf(
                        "Mbaraugba, Ovom Amaa Asaa, Abia state",
                        "+234 56543 96854"
                    )
                )
            }

            // ===== Destination details =====
            item {
                InfoCard(
                    title = "Destination details",
                    icon = "🏠",
                    details = listOf(
                        "1. 19 Ademola Alabi close, lagos",
                        "+234 70644 80655"
                    )
                )
            }

            // ===== Other details =====
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "📦 Other details",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Divider()

                        DetailRow(
                            label = "Package Items",
                            value = "Books ans stationary, Garri Ngwa"
                        )
                        DetailRow(
                            label = "Weight of items",
                            value = "1000kg"
                        )
                        DetailRow(
                            label = "Worth of Items",
                            value = "N50,000"
                        )
                        DetailRow(
                            label = "Tracking Number",
                            value = "R-7458-4567-4434-5854",
                            isHighlighted = true
                        )
                    }
                }
            }

            // ===== Charges =====
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "💰 Charges",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Divider()

                        ChargeRow(
                            label = "Delivery Charges",
                            value = "N2,500.00"
                        )
                        ChargeRow(
                            label = "Instant delivery",
                            value = "N300.00"
                        )
                        ChargeRow(
                            label = "Tax and Service Charges",
                            value = "N200.00"
                        )

                        Divider()

                        ChargeRow(
                            label = "Package total",
                            value = "N3000.00",
                            isTotal = true
                        )
                    }
                }
            }

            // ===== Информация о доставке =====
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "📌 Click on delivered for successful delivery and rate rider or report missing item",
                        fontSize = 14.sp,
                        color = Color(0xFFE65100),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // ===== Кнопки =====
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Кнопка "Report"
                    OutlinedButton(
                        onClick = onReportClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFF44336)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Report,
                            contentDescription = "Report",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Report",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Кнопка "Successful"
                    Button(
                        onClick = onSuccessfulClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Successful",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Successful",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ===== Компонент: Карточка информации =====
@Composable
fun InfoCard(
    title: String,
    icon: String,
    details: List<String>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "$icon $title",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Divider()

            details.forEach { detail ->
                Text(
                    text = detail,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

// ===== Компонент: Строка деталей =====
@Composable
fun DetailRow(
    label: String,
    value: String,
    isHighlighted: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            fontSize = if (isHighlighted) 14.sp else 14.sp,
            fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Normal,
            color = if (isHighlighted) Color(0xFF6200EE) else Color.Black,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.End
        )
    }
}

// ===== Компонент: Строка стоимости =====
@Composable
fun ChargeRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 16.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if (isTotal) Color.Black else Color.Gray
        )
        Text(
            text = value,
            fontSize = if (isTotal) 18.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Medium,
            color = if (isTotal) Color(0xFF6200EE) else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSendPackageScreen() {
    SendPackageScreen()
}