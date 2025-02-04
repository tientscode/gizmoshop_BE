package com.gizmo.gizmoshop.repository;

import com.gizmo.gizmoshop.entity.WithdrawalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gizmo.gizmoshop.entity.WalletAccount;

import java.util.Date;
import java.util.List;

@Repository
public interface WithdrawalHistoryRepository extends JpaRepository<WithdrawalHistory, Long> {
    List<WithdrawalHistory> findByWalletAccountInAndWithdrawalDateBetween(List<WalletAccount> walletAccounts, Date startDate, Date endDate);

    List<WithdrawalHistory> findByWalletAccountIn(List<WalletAccount> walletAccounts);

}

