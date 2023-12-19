using FundamentalsNumericalComputation


function q4a(β)
    m = 10
    d = LinRange(0, 2 * pi, m + 1)

    A = zeros(m+1, 3)

    for i in 1:m+1
        for j in 1:3
            if j == 1
                A[i,j] = 1
            elseif j == 2
                A[i,j] = sin(d[i])
            else  #j = 3
                A[i,j] = cos(β*d[i])
            end
        end
    end

    tA = transpose(A)
    nA = norm(A)
    Ap = inv(tA * A) * tA
    nAp = norm(Ap)
    cond = dot(nA, nAp)
    println("The condition number when β: ", β, " is ", cond)
end

function q4b(β)
    m = 10
    d = LinRange(0, 2 * pi, m + 1)

    A = zeros(m+1, 3)

    for i in 1:m+1
        for j in 1:3
            if j == 1
                A[i,j] = 1
            elseif j == 2
                A[i,j] = sin(d[i])^2
            else  #j = 3
                A[i,j] = cos(β*d[i])^2
            end
        end
    end

    tA = transpose(A)
    nA = norm(A)
    Ap = inv(tA * A) * tA
    nAp = norm(Ap)
    cond = dot(nA, nAp)
    println("The condition number when β: ", β, " is ", cond)
end

println("PART A")
q4a(2)
q4a(1+10^-1)
q4a(1+10^-2)
q4a(1+10^-3)
q4a(1+10^-4)
q4a(1+10^-5)
q4a(1+10^-6)
q4a(1+10^-7)
q4a(1+10^-8)

println("PART B")
q4b(2)
q4b(1+10^-1)
q4b(1+10^-2)
q4b(1+10^-3)
q4b(1+10^-4)
q4b(1+10^-5)
q4b(1+10^-6)
q4b(1+10^-7)
q4b(1+10^-8)

println("PART C")
println("TODO")
